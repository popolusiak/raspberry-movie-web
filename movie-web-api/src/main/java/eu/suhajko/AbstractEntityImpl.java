package eu.suhajko;

import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Stream;


/**
 * @MappedSuperclass Anotacia ktora definuje ze ide o parent entitu. Nie je pre nu samostatna tabulka,
 * stlpce ktore tu definujeme budu v tabulke potomka.
 * 
 * UserWithUserDetails extends AbstractEntityImpl - v tabulke employee bude stlpec ID
 * @author Ladislav
 *
 */
@MappedSuperclass
public abstract class AbstractEntityImpl implements AbstractEntity {
	

	protected Long id;

	@Override @Id
	@Column(name = "ID")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Override public void setId(Long id) {
		this.id = id;
	}

	
	
	/**
	 * Ak ma id to znamena ze entitu nainicializoval entityManager(db manager). To znamena
	 * ze bud prisla z databazy (SELECT) alebo je to nova entita ktoru entityManager inicializoval
	 * - vytvoril jej IDcko. Ak Je id == null entita nie je udrziavana entityManagerom.
	 * @return
	 */
	@Override @Transient
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}

	
	/**
	 * Pri entitach je treba overidovat equals https://developer.jboss.org/wiki/EqualsAndHashCode 
	 * Tu som sa inspiroval https://github.com/spring-projects/spring-data-jpa/blob/master/src/main/java/org/springframework/data/jpa/domain/AbstractPersistable.java
	 * 
	 * Ci su dva objekty rovnake v jave sa vie podla heshKodu ktory ma kazdy objekt, vytvara sa ked sa vola new Nieco().
	 * My vsak potrebujeme porovnavat ID_cka pretoze ak si vytiahneme jeden objekt z databazy, a potom znovu rovnaky objekt
	 * budu to z pohladu javy dva rozdielne objekty(budu mat rozne hashkody) ale v databaze je to rovnaky zaznam/rovnaky objekt
	 * - maju rovnake IDcka
	 */
	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(ClassUtils.getUserClass(obj))) {
			return false;
		}

		AbstractEntityImpl that = (AbstractEntityImpl) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	/**
	 * Ked overridujeme equals musime aj hashcode s pouzitim getId() prvku ktory porovnavame v equals.
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}

	public void assignThiEntityId(AbstractEntity ... entities){
		Stream.of(entities)
				.filter(e -> !Objects.isNull(e) && !(e.isNew() && !this.isNew()))
				.forEach(e -> e.setId(this.id));
	}


}
