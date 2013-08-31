package gr.brid.castamuv.domain.service;

import gr.brid.castamuv.domain.model.link.Link;
import gr.brid.castamuv.domain.model.music.Music;

public interface MusicGenerateService {
	Music generate(Link link);
}
