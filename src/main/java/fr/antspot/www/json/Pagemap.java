package fr.antspot.www.json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Pagemap {

	@Expose
	private List<Metatag> metatags = new ArrayList<Metatag>();

	@Expose
	private List<Person> person = new ArrayList<Person>();

	/**
	 * 
	 * @return The metatags
	 */
	public List<Metatag> getMetatags() {
		return metatags;
	}

	/**
	 * 
	 * @param metatags
	 *            The metatags
	 */
	public void setMetatags(List<Metatag> metatags) {
		this.metatags = metatags;
	}

	public Pagemap withMetatags(List<Metatag> metatags) {
		this.metatags = metatags;
		return this;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public Pagemap withPerson(List<Person> person) {
		this.person = person;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(metatags).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Pagemap) == false) {
			return false;
		}
		Pagemap rhs = ((Pagemap) other);
		return new EqualsBuilder().append(metatags, rhs.metatags).isEquals();
	}

}
