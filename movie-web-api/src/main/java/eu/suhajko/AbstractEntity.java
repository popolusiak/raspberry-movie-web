package eu.suhajko;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * Created by marek.melis on 12/20/16.
 */
public interface AbstractEntity {
	@Id
	@Column(name = "ID")
	@GeneratedValue Long getId();

	void setId(Long id);

	@Transient
	default boolean isNew(){
		return getId() != null;
	}
}
