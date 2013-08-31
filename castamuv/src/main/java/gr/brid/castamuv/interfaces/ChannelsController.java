package gr.brid.castamuv.interfaces;

import javax.inject.Inject;

import gr.brid.castamuv.application.user.EditingMusicboxService;
import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChannelsController {

	private UserMusicboxGettingService service;

	private EditingMusicboxService edit;

	@RequestMapping(value = "/channels", method = RequestMethod.GET)
	public String mychannels(Model model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			return this.channels(auth.getName(), model);
		else
			return "redirect:/";
	}

	@RequestMapping(value = "/channels/{userId}")
	public String channels(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("channels", service.channels(new UserId(userId)));
		return "channels";
	}

	@RequestMapping(value = "/channels", method = RequestMethod.POST)
	public String post(String title) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null)
			edit.newChannel(new UserId(auth.getName()), title);

		return "redirect:/channels";
	}

	@RequestMapping(value = "/channel")
	public String channel(String channelId, Model model) {
		model.addAttribute("musics", service.musics(new ChannelId(channelId)));
		model.addAttribute("channelId", channelId);

		return "channel";
	}

	@Inject
	public void setService(UserMusicboxGettingService service) {
		this.service = service;
	}

	@Inject
	public void setEdit(EditingMusicboxService edit) {
		this.edit = edit;
	}

}
