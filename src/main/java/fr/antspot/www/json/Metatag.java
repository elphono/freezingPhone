
package fr.antspot.www.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Metatag {

    @Expose
    private String viewport;

    /**
     * 
     * @return
     *     The viewport
     */
    public String getViewport() {
        return viewport;
    }

    /**
     * 
     * @param viewport
     *     The viewport
     */
    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public Metatag withViewport(String viewport) {
        this.viewport = viewport;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(viewport).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metatag) == false) {
            return false;
        }
        Metatag rhs = ((Metatag) other);
        return new EqualsBuilder().append(viewport, rhs.viewport).isEquals();
    }

}
