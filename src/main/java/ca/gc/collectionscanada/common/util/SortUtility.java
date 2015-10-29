package ca.gc.collectionscanada.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.gc.collectionscanada.domain.Department;
import ca.gc.collectionscanada.domain.DepartmentWiseURLs;
import ca.gc.collectionscanada.domain.SingleURL;

public class SortUtility {
	protected static final Log logger = LogFactory.getLog(SortUtility.class);

	public SortUtility() {
	}

	public static SingleURL[] sortAlphabetically(SingleURL[] masterArray,
			String letter) throws MalformedURLException {

		// Initialize new List;
		ArrayList<SingleURL> newList = new ArrayList<SingleURL>();

		if (letter.equalsIgnoreCase("[0-9]")) {

			for (SingleURL aURL : masterArray) {

				if (aURL.getUrlDesc().startsWith("http")) {
					if (aURL.getUrlDesc().substring(7).startsWith("www")) {

						if (Character.isDigit(aURL.getUrlDesc().substring(11)
								.charAt(0))) {
							newList.add(aURL);
						}
					} else {
						if (Character.isDigit(aURL.getUrlDesc().substring(7)
								.charAt(0))) {
							newList.add(aURL);
						}
					}
				} else { // for URL list starts with www
					if (Character.isDigit(aURL.getUrlDesc().substring(4)
							.charAt(0))) {
						newList.add(aURL);
					}
				}
			} // end of for loop
		}

		else { // for non numeric URLs
			for (SingleURL aURL : masterArray) {
				if (aURL.getUrlDesc().startsWith("http")) {
					if (aURL.getUrlDesc().substring(7).startsWith("www")) {
						if (aURL.getUrlDesc().substring(11)
								.startsWith(letter.toLowerCase())) {
							// aURL.setUrlDesc("http://" + aURL.getUrlDesc());
							aURL.setUrlDesc("http://"
									+ new URL(aURL.getUrlDesc()).getHost()
									+ "/");
							newList.add(aURL);
						}
					} else {
						if (aURL.getUrlDesc().substring(7)
								.startsWith(letter.toLowerCase())) {
							aURL.setUrlDesc("http://www."
									+ new URL(aURL.getUrlDesc()).getHost()
									+ "/");
							newList.add(aURL);
						}
					}
				} else {
					if (aURL.getUrlDesc().substring(4)
							.startsWith(letter.toLowerCase())) {
						newList.add(aURL);
					}
				}
			}
		}

		SingleURL[] toArray = new SingleURL[newList.size()];
		newList.toArray(toArray);

		Arrays.sort(toArray, new Comparator<SingleURL>() {

			@Override
			public int compare(SingleURL o1, SingleURL o2) {
				return o1.getUrlDesc().compareTo(o2.getUrlDesc());
			}

		});
		return toArray;
	}

	public static List<DepartmentWiseURLs> sortDeptAlphabetically(
			List<DepartmentWiseURLs> list, String letter) {
		// Initialize new List;
		List<DepartmentWiseURLs> newList = new ArrayList<DepartmentWiseURLs>();

		if (letter.equalsIgnoreCase("[0-9]")) {
			for (DepartmentWiseURLs aURL : list) {
				if (Character.isDigit(aURL.getEngDesc().charAt(0))) {
					newList.add(aURL);
				}
			} // end of for loop
		}

		else { // for non numeric URLs
			for (DepartmentWiseURLs aURL : list) {
				if (aURL.getEngDesc().toLowerCase()
						.startsWith(letter.toLowerCase())) {
					newList.add(aURL);
				}
			}
			Collections.sort(newList, new Comparator<DepartmentWiseURLs>() {

				@Override
				public int compare(DepartmentWiseURLs o1, DepartmentWiseURLs o2) {

					// TODO Auto-generated method stub
					return o1.getEngDesc().compareTo(o2.getEngDesc());
				}

			});
		}

		return newList;
	}

	public static DepartmentWiseURLs[] sortDeptAlphabetically(
			DepartmentWiseURLs[] array, String letter) {
		// Initialize new List;
		List<DepartmentWiseURLs> newList = new ArrayList<DepartmentWiseURLs>();

		if (letter.equalsIgnoreCase("[0-9]")) {
			for (DepartmentWiseURLs aURL : array) {
				if (Character.isDigit(aURL.getEngDesc().charAt(0))) {
					newList.add(aURL);
				}
			} // end of for loop
		}

		else { // for non numeric URLs
			for (DepartmentWiseURLs aURL : array) {
				if (aURL.getEngDesc().toLowerCase()
						.startsWith(letter.toLowerCase())) {
					newList.add(aURL);
				}
			}
			Collections.sort(newList, new Comparator<DepartmentWiseURLs>() {

				@Override
				public int compare(DepartmentWiseURLs o1, DepartmentWiseURLs o2) {

					// TODO Auto-generated method stub
					return o1.getEngDesc().compareTo(o2.getEngDesc());
				}

			});
		}

		DepartmentWiseURLs[] newArray = new DepartmentWiseURLs[newList.size()];

		return newList.toArray(newArray);
	}

	public static DepartmentWiseURLs[] sortDeptAlphabeticallyEng(
			DepartmentWiseURLs[] masterArray, String letter) {
		// Initialize new List;
		ArrayList<DepartmentWiseURLs> newList = new ArrayList<DepartmentWiseURLs>();

		if (letter.equalsIgnoreCase("[0-9]")) {
			for (DepartmentWiseURLs aURL : masterArray) {
				if (Character.isDigit(aURL.getEngDesc().charAt(0))) {
					newList.add(aURL);
				}
			} // end of for loop
		}

		else { // for non numeric URLs
			for (DepartmentWiseURLs aURL : masterArray) {
				if (aURL.getEngDesc().toLowerCase()
						.startsWith(letter.toLowerCase())) {
					newList.add(aURL);
				}
			}
		}

		DepartmentWiseURLs[] newArray = new DepartmentWiseURLs[newList.size()];
		newList.toArray(newArray);

		Arrays.sort(newArray, new Comparator<DepartmentWiseURLs>() {

			@Override
			public int compare(DepartmentWiseURLs o1, DepartmentWiseURLs o2) {

				// TODO Auto-generated method stub
				return o1.getEngDesc().compareTo(o2.getEngDesc());
			}

		});

		return newArray;
	}

	public static DepartmentWiseURLs[] sortDeptAlphabeticallyFr(
			DepartmentWiseURLs[] masterArray, String letter) {
		// Initialize new List;
		ArrayList<DepartmentWiseURLs> newList = new ArrayList<DepartmentWiseURLs>();

		if (letter.equalsIgnoreCase("[0-9]")) {
			for (DepartmentWiseURLs aURL : masterArray) {
				if (Character.isDigit(aURL.getFrDesc().charAt(0))) {
					newList.add(aURL);
				}
			} // end of for loop
		}

		else { // for non numeric URLs
			for (DepartmentWiseURLs aURL : masterArray) {
				if (aURL.getFrDesc().toLowerCase()
						.startsWith(letter.toLowerCase())) {
					newList.add(aURL);
				}
			}
		}

		DepartmentWiseURLs[] newArray = new DepartmentWiseURLs[newList.size()];
		newList.toArray(newArray);

		Arrays.sort(newArray, new Comparator<DepartmentWiseURLs>() {

			@Override
			public int compare(DepartmentWiseURLs o1, DepartmentWiseURLs o2) {
				return o1.getFrDesc().compareTo(o2.getFrDesc());
			}
		});

		return newArray;
	}

	public static Department[] sortDeptAlphabetsEng(Department masterArray[],
			String letter) {
		ArrayList<Department> newList = new ArrayList<Department>();
		if (letter.equalsIgnoreCase("[0-9]")) {
			Department arr$[] = masterArray;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++) {
				Department dept = arr$[i$];
				if (Character.isDigit(dept.getDeptNameEng().charAt(0)))
					newList.add(dept);
			}

		} else {
			Department arr$[] = masterArray;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++) {
				Department dept = arr$[i$];
				if (dept.getDeptNameEng().toLowerCase()
						.startsWith(letter.toLowerCase()))
					newList.add(dept);
			}

		}
		Department newArray[] = new Department[newList.size()];
		newList.toArray(newArray);
		Arrays.sort(newArray, new Comparator<Department>() {

			public int compare(Department o1, Department o2) {
				return o1.getDeptNameEng().compareTo(o2.getDeptNameEng());
			}
		});
		return newArray;
	}

	public static Department[] sortDeptAlphabetsFr(Department masterArray[],
			String letter) {
		ArrayList<Department> newList = new ArrayList<Department>();
		if (letter.equalsIgnoreCase("[0-9]")) {
			Department arr$[] = masterArray;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++) {
				Department dept = arr$[i$];
				if (Character.isDigit(dept.getDeptNameFr().charAt(0)))
					newList.add(dept);
			}

		} else {
			Department arr$[] = masterArray;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++) {
				Department dept = arr$[i$];
				if (dept.getDeptNameFr().toLowerCase()
						.startsWith(letter.toLowerCase()))
					newList.add(dept);
			}

		}
		Department newArray[] = new Department[newList.size()];
		newList.toArray(newArray);
		Arrays.sort(newArray, new Comparator<Department>() {

			public int compare(Department o1, Department o2) {
				return o1.getDeptNameFr().compareTo(o2.getDeptNameFr());
			}
		});
		return newArray;
	}
}