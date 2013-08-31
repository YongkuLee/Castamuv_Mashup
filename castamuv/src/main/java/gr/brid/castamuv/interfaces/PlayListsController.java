package gr.brid.castamuv.interfaces;

import javax.inject.Inject;

import gr.brid.castamuv.application.playlist.EditingPlaylistService;
import gr.brid.castamuv.application.user.EditingMusicboxService;
import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.shared.DefaultValues;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlayListsController {

	private UserMusicboxGettingService service;

	private EditingMusicboxService edit;

	private EditingPlaylistService editList;

	@RequestMapping(value = "/playlists", method = RequestMethod.GET)
	public String playLists(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			model.addAttribute("playlists",
					service.playLists(new UserId(auth.getName())));

			model.addAttribute("channels",
					service.channels(new UserId(DefaultValues.STORE_ID)));
		}
		return "playlists";
	}

	@RequestMapping(value = "/playlists2", method = RequestMethod.GET)
	public String playLists2pla(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			model.addAttribute("playlists",
					service.playLists(new UserId(auth.getName())));

			model.addAttribute("channels",
					service.channels(new UserId(DefaultValues.STORE_ID)));
		}
		return "playlists2";
	}

	@RequestMapping(value = "/playlists", method = RequestMethod.POST)
	public String post(String title, String description) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			edit.newPlayList(title, description, new UserId(auth.getName()));
		return "redirect:/playlists";
	}

	@RequestMapping(value = "/playlists/{listId}", method = RequestMethod.GET)
	public String musics(@PathVariable("listId") String listId, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			model.addAttribute("playlists",
					service.playLists(new UserId(auth.getName())));
			model.addAttribute("musics", service.musics(new PlayListId(listId)));
			model.addAttribute("channels",
					service.channels(new UserId(DefaultValues.STORE_ID)));
		}
		return "playlists";
	}

	@RequestMapping(value = "/playlists/{listId}", method = RequestMethod.POST)
	public String postMusic(@PathVariable("listId") String listId,
			String title, String description, String url) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			editList.addMusic(url, title, description, new PlayListId(listId));
		}
		return "redirect:/playlists/" + listId;
	}

	@RequestMapping(value = "/playlists/{listId}", method = RequestMethod.POST, params = { "musicId" })
	public String deleteMusic(@PathVariable("listId") String listId,
			String musicId) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			editList.deleteMusic(new MusicId(musicId), new PlayListId(listId));
		}
		return "redirect:/playlists/" + listId;
	}

	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public String folder(String channelId, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth != null) {
			model.addAttribute("playlists",
					service.playLists(new UserId(auth.getName())));

			model.addAttribute("channels",
					service.channels(new UserId(DefaultValues.STORE_ID)));

			model.addAttribute("channelId", channelId);

			model.addAttribute("user", service.user(new UserId(auth.getName())));
		}
		return "folder";
	}

	@RequestMapping(value = "/folder/{listId}", method = RequestMethod.GET)
	public String folderMusics(@PathVariable("listId") String listId,
			String channelId, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			model.addAttribute("playlists",
					service.playLists(new UserId(auth.getName())));
			model.addAttribute("musics", service.musics(new PlayListId(listId)));
			model.addAttribute("channels",
					service.channels(new UserId(DefaultValues.STORE_ID)));
			model.addAttribute("channelId", channelId);
			model.addAttribute("playlistId", listId);
		}
		return "musics";
	}

	@Inject
	public void setGettingService(UserMusicboxGettingService gettingService) {
		this.service = gettingService;
	}

	@Inject
	public void setEdit(EditingMusicboxService edit) {
		this.edit = edit;
	}

	@Inject
	public void setEditList(EditingPlaylistService editList) {
		this.editList = editList;
	}
}
