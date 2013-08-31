package gr.brid.castamuv.application.playlist.impl;

import gr.brid.castamuv.application.playlist.EditingPlaylistService;
import gr.brid.castamuv.domain.model.link.Link;
import gr.brid.castamuv.domain.model.link.LinkType;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.music.MusicRepository;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.playlist.PlayListRepository;
import gr.brid.castamuv.domain.service.MusicGenerateService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EditingPlaylistServiceImpl implements EditingPlaylistService {

	private MusicGenerateService musicGenerateService;

	private MusicRepository musicRepository;

	private PlayListRepository playListRepository;

	@Override
	public Music addMusic(String url, String title, String description,
			PlayListId playListId) {
		PlayList playList = playListRepository.findOne(playListId);

		Link link = new Link(LinkType.youtube, url);

		Music music = musicGenerateService.generate(link);
		music.setTitle(title);
		music.setDescription(description);
		musicRepository.save(music);

		playList.getMusics().add(music);
		playListRepository.save(playList);

		return music;
	}

	@Override
	public void deleteMusic(MusicId musicId, PlayListId playListId) {
		PlayList playList = playListRepository.findOne(playListId);

		Music music = musicRepository.findOne(musicId);

		for (Music m : playList.getMusics()) {
			if (m.sameEntityAs(music)) {
				playList.getMusics().remove(m);
			}
		}

		playListRepository.save(playList);
	}

	@Inject
	public void setMusicGenerateService(
			MusicGenerateService musicGenerateService) {
		this.musicGenerateService = musicGenerateService;
	}

	@Inject
	public void setPlayListRepository(PlayListRepository playListRepository) {
		this.playListRepository = playListRepository;
	}

	@Inject
	public void setMusicRepository(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

}
