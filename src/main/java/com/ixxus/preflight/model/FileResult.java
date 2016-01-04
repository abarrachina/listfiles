package com.ixxus.preflight.model;

//TODO: Auto-generated Javadoc
/**
* The Class FileResult.
* * @author Nazareth Jiménez
*/
public class FileResult {

	/** The name. */
	private String name;
	
	/** The is correct. */
	private String isCorrect;
		
	/**
	 * Instantiates a new file.
	 */
	public FileResult(){
		
	}
	
	/**
	 * Instantiates a new file.
	 *
	 * @param name the name
	 * @param isCorrect the is correct
	 */
	public FileResult(String name, String isCorrect) {
		super();
		this.name = name;
		this.isCorrect = isCorrect;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the checks if is correct.
	 *
	 * @return the checks if is correct
	 */
	public String getIsCorrect() {
		return isCorrect;
	}
	
	/**
	 * Sets the checks if is correct.
	 *
	 * @param isCorrect the new checks if is correct
	 */
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}
