package gr.brid.castamuv.interfaces;

import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.shared.DefaultValues;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayerController {

	private UserMusicboxGettingService service;

	@RequestMapping("/players")
	public String player(Model model) {

		List<Channel> channels = service.channels(new UserId(
				DefaultValues.STORE_ID));

		model.addAttribute("channels", channels);

		return "players";
	}

	@RequestMapping(value = "/player/{channelId}", method = RequestMethod.GET)
	public String mobile(@PathVariable(value = "channelId") String channelId,
			Model model) {
		model.addAttribute("channelId", channelId);
		return "home";
	}

	@RequestMapping(value = "/player/{channelId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> playLists(
			@PathVariable(value = "channelId") String channelId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", service.musics(new ChannelId(channelId)));

		return map;
	}

	@RequestMapping(value = "/player/{channelId}/next")
	@ResponseBody
	public Map<String, Object> next(
			@PathVariable(value = "channelId") String channelId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		service.next(new ChannelId(channelId));
		map.put("success", true);
		return map;
	}

	@Inject
	public void setService(UserMusicboxGettingService service) {
		this.service = service;
	}
}
