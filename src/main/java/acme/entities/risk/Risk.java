
package acme.entities.risk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class Risk extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^R-[0-9]{3}$", message = "{validation.Risk.reference}")
	private String				reference;

	@NotNull
	@Temporal(TemporalType.DATE)
	@PastOrPresent
	private Date				identificationDate;

	@Positive
	private double				impact;

	@Positive
	@Digits(integer = 3, fraction = 2)
	@Min(0)
	@Max(100)
	private double				probability;

	@NotBlank
	@Length(max = 100, min = 0)
	private String				description;

	@URL
	@Length(max = 255, min = 0)
	private String				link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public Double value() {

		return this.impact * this.probability;

	}

	// Relationships ----------------------------------------------------------

}
