package edu.uiuc.whosinline.data;

public class HoursSchedule {
	private String hoursMonday;
	private String hoursTuesday;
	private String hoursWednesday;
	private String hoursThursday;
	private String hoursFriday;
	private String hoursSaturday;
	private String hoursSunday;
	
	public HoursSchedule(String hoursMonday, String hoursTuesday,
			String hoursWednesday, String hoursThursday, String hoursFriday,
			String hoursSaturday, String hoursSunday) {
		this.hoursMonday = hoursMonday;
		this.hoursTuesday = hoursTuesday;
		this.hoursWednesday = hoursWednesday;
		this.hoursThursday = hoursThursday;
		this.hoursFriday = hoursFriday;
		this.hoursSaturday = hoursSaturday;
		this.hoursSunday = hoursSunday;
	}

	public String getHoursMonday() {
		return hoursMonday;
	}

	public void setHoursMonday(String hoursMonday) {
		this.hoursMonday = hoursMonday;
	}

	public String getHoursTuesday() {
		return hoursTuesday;
	}

	public void setHoursTuesday(String hoursTuesday) {
		this.hoursTuesday = hoursTuesday;
	}

	public String getHoursWednesday() {
		return hoursWednesday;
	}

	public void setHoursWednesday(String hoursWednesday) {
		this.hoursWednesday = hoursWednesday;
	}

	public String getHoursThursday() {
		return hoursThursday;
	}

	public void setHoursThursday(String hoursThursday) {
		this.hoursThursday = hoursThursday;
	}

	public String getHoursFriday() {
		return hoursFriday;
	}

	public void setHoursFriday(String hoursFriday) {
		this.hoursFriday = hoursFriday;
	}

	public String getHoursSaturday() {
		return hoursSaturday;
	}

	public void setHoursSaturday(String hoursSaturday) {
		this.hoursSaturday = hoursSaturday;
	}

	public String getHoursSunday() {
		return hoursSunday;
	}

	public void setHoursSunday(String hoursSunday) {
		this.hoursSunday = hoursSunday;
	}
}