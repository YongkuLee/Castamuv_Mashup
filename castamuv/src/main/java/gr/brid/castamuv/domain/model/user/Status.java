package gr.brid.castamuv.domain.model.user;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Status {
	@ManyToOne
	@JoinColumn
	private Music music;

	@ManyToOne
	@JoinColumn
	private PlayList playList;

	private boolean play;

	public Status() {
		this.music = null;
		this.playList = null;
		this.play = false;
	}

	/**
	 * 이 함수는 현제 플래잉하고 있는 음악을 반환합니다. 단 플래이 하지 않고 있을 경우 [null]값을 반환합니다.
	 * 
	 * @return
	 */
	public Music now() {
		if (play)
			return music;
		else
			return null;
	}
}
