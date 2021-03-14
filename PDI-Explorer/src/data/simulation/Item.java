package data.simulation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * The items are separated in five different types. Health, speed, damage, scope
 * and communication range. The boost is a value added to the basic explorer's
 * statistics.
 * 
 * @author Léo
 *
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Item {

	private String id;

	private String type;

	private int tier;

	@XmlTransient
	private String name;

	@XmlTransient
	private String description;

	private int boost;

	public Item() {

	}

	public Item(String id, String type, int boost) {
		this.id = id;
		this.type = type;
		this.boost = boost;
	}

	public Item(String id, String type, int tier, int boost) {
		this.id = id;
		this.type = type;
		this.tier = tier;
		this.boost = boost;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "tier")
	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "boost")
	public int getBoost() {
		return boost;
	}

	public void setBoost(int boost) {
		this.boost = boost;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", tier=" + tier + ", name=" + name + ", description="
				+ description + ", boost=" + boost + "]";
	}

}
