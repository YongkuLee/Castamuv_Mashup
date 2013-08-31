package gr.brid.castamuv.infrastructure.music;

import gr.brid.castamuv.domain.model.link.Link;
import gr.brid.castamuv.domain.model.link.LinkRepository;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.service.MusicGenerateService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class YoutubeMusicGenerateService implements MusicGenerateService {

	private LinkRepository linkRepository;

	@Override
	public Music generate(Link link) {
		Link linkObj = linkRepository.findByValue(link.getValue());

		if (linkObj == null) {
			link.setThumbnail(generateYoutubeTumbnail(link.getValue()));
			linkRepository.save(link);
			Music music = new Music(link);
			return music;
		} else {
			return new Music(linkObj);
		}
	}

	public String generateYoutubeTumbnail(String url) {
		return "http://img.youtube.com/vi/" + url + "/0.jpg";
	}

	@Inject
	public void setLinkRepository(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}
}
