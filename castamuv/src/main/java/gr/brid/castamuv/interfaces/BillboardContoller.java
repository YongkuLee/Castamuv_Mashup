package gr.brid.castamuv.interfaces;

import gr.brid.castamuv.interfaces.dtos.BillboardDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class BillboardContoller {
	@RequestMapping("/billboard")
	public String billboard(Model model) throws Exception {
		List<BillboardDTO> dtos = new ArrayList<BillboardDTO>();

		URL url = new URL("http://www.billboard.com/rss/charts/hot-100");
		XmlReader xmlReader = null;

		try {

			xmlReader = new XmlReader(url);
			SyndFeed feeder = new SyndFeedInput().build(xmlReader);
			System.out.println("Title Value " + feeder.getAuthor());

			for (Iterator iterator = feeder.getEntries().iterator(); iterator
					.hasNext();) {
				SyndEntry syndEntry = (SyndEntry) iterator.next();
				System.out.println(syndEntry.getTitle());

				BillboardDTO dto = new BillboardDTO(syndEntry.getTitle());
				
				dtos.add(dto);
			}
		} finally {
			if (xmlReader != null)
				xmlReader.close();
		}

		model.addAttribute("list", dtos);
		return "billboard";
	}
}
