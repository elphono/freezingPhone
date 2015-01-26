
package fr.antspot.www.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Url {

    @Expose
    private String type;
    @Expose
    private String template;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Url withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 
     * @param template
     *     The template
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    public Url withTemplate(String template) {
        this.template = template;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(template).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Url) == false) {
            return false;
        }
        Url rhs = ((Url) other);
        return new EqualsBuilder().append(type, rhs.type).append(template, rhs.template).isEquals();
    }

}
