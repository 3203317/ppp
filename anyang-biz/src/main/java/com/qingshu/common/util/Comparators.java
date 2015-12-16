package com.qingshu.common.util;

import java.util.Comparator;

import com.qingshu.base.vo.ReportIndex;

public class Comparators implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		if (o1 instanceof String) {
			return compare((String) o1, (String) o2);
		} else if (o1 instanceof Integer) {
			return compare((Integer) o1, (Integer) o2);
		} else if (o1 instanceof ReportIndex) {
			return compare((ReportIndex) o1, (ReportIndex) o2);
		} else {
			System.err.println("未找到合适的比较器");
			return 1;

		}
	}

	public int compare(String o1, String o2) {
		String s1 = (String) o1;
		String s2 = (String) o2;
		int len1 = s1.length();
		int len2 = s2.length();
		int n = Math.min(len1, len2);
		char v1[] = s1.toCharArray();
		char v2[] = s2.toCharArray();
		int pos = 0;

		while (n-- != 0) {
			char c1 = v1[pos];
			char c2 = v2[pos];
			if (c1 != c2) {
				return c1 - c2;
			}
			pos++;
		}
		return len1 - len2;
	}

	public int compare(Integer o1, Integer o2) {
		int val1 = o1.intValue();
		int val2 = o2.intValue();
		return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));

	}

	public int compare(Boolean o1, Boolean o2) {

		return (o1.equals(o2) ? 0 : (o1.booleanValue() == true ? 1 : -1));

	}
	public int compare(ReportIndex o1, ReportIndex o2) {
		Integer index1 = o1.getIndexType();
		Integer index2 = o2.getIndexType();
		return (compare(index1, index2) == 0 ? 0 : compare(index1, index2));

	}

}
