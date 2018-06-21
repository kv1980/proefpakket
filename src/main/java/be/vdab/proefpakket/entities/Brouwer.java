package be.vdab.proefpakket.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name="brouwers")
public class Brouwer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String naam;
	private String straat;
	private String huisNr;
	@Many-To-One
	private Gemeente gemeente;
	private long ondernemingsNr;

}
