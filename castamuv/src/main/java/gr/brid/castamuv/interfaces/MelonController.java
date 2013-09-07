package gr.brid.castamuv.interfaces;

import gr.brid.castamuv.interfaces.dtos.MelonDTO;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class MelonController {

	@RequestMapping("/melon")
	public String melon(Model model) throws Exception {

		List<MelonDTO> dtos = new ArrayList<MelonDTO>();

		URL url = new URL(
				"http://apis.skplanetx.com/melon/charts/realtime?count=100&page=0&version=1");

		HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();

		urlconn.setDoInput(true);
		urlconn.setDoOutput(true);
		urlconn.setRequestMethod("GET");
		urlconn.setRequestProperty("Accept", "application/json");
		urlconn.setRequestProperty("x-skpop-userId", "ianova");
		urlconn.setRequestProperty("Accept-Language", "ko_KR");
		urlconn.setRequestProperty("access_token",
				"43859217-c6f3-4586-b0a2-4afb504c83a3");
		urlconn.setRequestProperty("appKey",
				"5176afa9-3db8-3a08-96cc-b1b53fc7046a");

		XmlReader xmlReader = null;

		try {

			xmlReader = new XmlReader(urlconn.getInputStream());
			SyndFeed feeder = new SyndFeedInput().build(xmlReader);
			System.out.println("Title Value " + feeder.getAuthor());

			for (Object entry : feeder.getEntries()) {
				SyndEntry syndEntry = (SyndEntry) entry;
				System.out.println(syndEntry.getTitle());

				MelonDTO dto = new MelonDTO(syndEntry.getTitle());

				dtos.add(dto);
			}

		} finally {

		}
		model.addAttribute("list", dtos);
		return "melon";
	}
}
