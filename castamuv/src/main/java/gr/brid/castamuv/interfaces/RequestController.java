package gr.brid.castamuv.interfaces;

import javax.inject.Inject;

import gr.brid.castamuv.application.user.RequestService;
import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestController {

	@Inject
	private UserMusicboxGettingService service;

	@Inject
	private RequestService requestService;

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String request(String musicId, String channelId, String playListId,
			Model model) {
		Music music = service.music(new MusicId(musicId));
		model.addAttribute("music", music);
		model.addAttribute("channelId", channelId);
		model.addAttribute("playListId", playListId);
		return "request";
	}

	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String post(String musicId, String playListId, String channelId,
			Model model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserId userId;
		if (auth.getName().equals("anonymousUser"))
			userId = new UserId(0);
		else
			userId = new UserId(auth.getName());

		requestService
				.request(new MusicId(musicId), new PlayListId(playListId),
						new ChannelId(channelId), 0, userId);

		return "redirect:/request?musicId=" + musicId + "&playListId="
				+ playListId + "&channelId=" + channelId;
	}
}
