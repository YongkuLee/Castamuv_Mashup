package gr.brid.castamuv.interfaces;

import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.interfaces.dtos.YoutubeDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

@Controller
public class YoutubeController {

	private String apiKey;

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/** Global instance of the max number of videos we want returned. */
	private static final long NUMBER_OF_VIDEOS_RETURNED = 30;

	/** Global instance of Youtube object to make all API requests. */
	private static YouTube youtube;

	@Inject
	private UserMusicboxGettingService service;

	@RequestMapping("/search")
	public String search(@RequestParam(required = false) String text,
			@RequestParam(required = false) String playlistId, Model model)
			throws IOException {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		List<PlayList> list = service.playLists(new UserId(auth.getName()));

		List<YoutubeDTO> dtos = new ArrayList<YoutubeDTO>();

		if (text != null) {
			try {
				/*
				 * The YouTube object is used to make all API requests. The last
				 * argument is required, but because we don't need anything
				 * initialized when the HttpRequest is initialized, we override
				 * the interface and provide a no-op function.
				 */
				youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY,
						new HttpRequestInitializer() {
							public void initialize(HttpRequest request)
									throws IOException {
							}
						}).setApplicationName("youtube-cmdline-search-sample")
						.build();

				// Get query term from user.
				String queryTerm = text;

				YouTube.Search.List search = youtube.search()
						.list("id,snippet");
				/*
				 * It is important to set your developer key from the Google
				 * Developer Console for non-authenticated requests (found under
				 * the API Access tab at this link: code.google.com/apis/). This
				 * is good practice and increased your quota.
				 */
				search.setKey(apiKey);
				search.setQ(queryTerm);
				/*
				 * We are only searching for videos (not playlists or channels).
				 * If we were searching for more, we would add them as a string
				 * like this: "video,playlist,channel".
				 */
				search.setType("video");
				/*
				 * This method reduces the info returned to only the fields we
				 * need and makes calls more efficient.
				 */
				search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
				search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
				SearchListResponse searchResponse = search.execute();

				List<SearchResult> searchResultList = searchResponse.getItems();

				for (SearchResult singleVideo : searchResultList) {
					ResourceId rId = singleVideo.getId();

					// Double checks the kind is video.
					if (rId.getKind().equals("youtube#video")) {
						Thumbnail thumbnail = singleVideo.getSnippet()
								.getThumbnails().get("default");

						dtos.add(new YoutubeDTO(rId.getVideoId(), singleVideo
								.getSnippet().getTitle(), thumbnail.getUrl()));
					}

				}

			} catch (GoogleJsonResponseException e) {
				System.err.println("There was a service error: "
						+ e.getDetails().getCode() + " : "
						+ e.getDetails().getMessage());
			} catch (IOException e) {
				System.err.println("There was an IO error: " + e.getCause()
						+ " : " + e.getMessage());
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

		model.addAttribute("list", dtos);
		model.addAttribute("playlistId", playlistId);

		model.addAttribute("playlists", list);

		return "youtube";
	}

	@Value("AIzaSyDjkvUP1dnbXBJ2WdrZE-JnHafiUXwyJaA")
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
