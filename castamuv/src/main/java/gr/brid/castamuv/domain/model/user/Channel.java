package gr.brid.castamuv.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Channel {

	@Id
	private ChannelId id;

	private String title;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Stream> streams;

	private Location location;

	@ManyToOne
	@JoinColumn
	private User user;

	// start at number 1
	private int current;

	public Channel(User user) {
		this.id = new ChannelId();
		this.current = 1;
		this.streams = new ArrayList<Stream>();
		this.user = user;
	}

	public List<Stream> getCurrentStreamList() {
		if (this.current == 0) {
			this.current = 1;
		} else if (this.streams.size() == 0) {
			return this.streams;
		}
		return this.streams.subList(this.current - 1, this.streams.size());
	}

	public Stream getCurrentStream() {
		return this.streams.get(this.current - 1);
	}

	public Stream nextStream() {
		if (isLast())
			return getCurrentStream();
		return this.streams.get(this.current++);
	}

	public Stream prevStream() {
		if (isFirst())
			return getCurrentStream();
		return this.streams.get((--this.current) - 1);
	}

	public boolean isLast() {
		return streams.size() <= current;
	}

	public boolean isFirst() {
		return current == 1;
	}
}
