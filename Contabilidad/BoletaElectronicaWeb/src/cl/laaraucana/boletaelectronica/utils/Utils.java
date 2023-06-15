package cl.laaraucana.boletaelectronica.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

public class Utils {

	
	public static List<OrigenBoletaVo> processList(List<OrigenBoletaVo> list) {
		Collections.sort(list, new Comparator<OrigenBoletaVo>() {
			@Override
			public int compare(OrigenBoletaVo origen1, OrigenBoletaVo origen2) {
				return String.valueOf(origen1.getFOLIO()).compareTo(String.valueOf(origen2.getFOLIO()));
			}
		});
		int i = 0;
		int j = 0;
		while (j < list.size() - 1) {
			String folio = list.get(i).getFOLIO();
			if (list.get(i + 1).getFOLIO().contains(folio)) {
				list.remove(list.get(i + 1));
			} else {
				i++;
			}
			j++;
		}
		return list;
	}

}
