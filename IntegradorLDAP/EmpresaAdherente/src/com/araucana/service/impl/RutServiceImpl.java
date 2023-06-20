package com.araucana.service.impl;

import com.araucana.service.RutService;

public class RutServiceImpl implements RutService {

	public boolean validate(String rutFull) {
		int rayaPos = 0;
		int rut = 0;
		String dv = "";
		String dvV = "";

		rutFull = this.clearRut(rutFull);

		rayaPos = rutFull.indexOf('-');
		if (rayaPos >= 0) {
			rut = Integer.parseInt(rutFull.substring(0, rayaPos));
			dv = rutFull.substring(rayaPos + 1);
		} else {
			rayaPos = rutFull.length() - 1;
			rut = Integer.parseInt(rutFull.substring(0, rayaPos));
			dv = rutFull.substring(rayaPos);
		}
		dv = dv.toLowerCase();

		dvV = this.dv(rut).toLowerCase();
		return dv.equals(dvV);
	}

	public String dv(int n) {
		Integer m = 0, s = 1;
		for (; n != 0; n = (int) Math.floor(n /= 10))
			s = (s + n % 10 * (9 - m++ % 6)) % 11;
		return (s > 0) ? String.valueOf(s - 1) : "k";

		/**
		 * <code>
		int M = 0, S = 1;
		for (; T > 0; T = (int)Math.floor(T / 10))
			S = (S + (int) T % 10 * (9 - M++ % 6)) % 11;
		return "" + (S > 0 ? S - 1 : 'k');
		</code>
		 */
	}

	public String clearRut(String dirtyRut) {
		String[] permitChars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "k", "K" };
		String out = "";
		String s = "";// , lCh="";

		for (int i = 0; i < dirtyRut.length(); i++) {
			s = dirtyRut.substring(i, i + 1);

			for (String lCh : permitChars) {
				if (s.equals(lCh)) {
					out += s;
					break;
				}
			}
		}

		if (out.indexOf('-') == -1 && out.length() >= 2) {
			out = out.substring(0, out.length() - 1) + '-' + out.substring(out.length() - 1);
		}

		return out;
	}

	@Override
	public int extractNumber(String rutFull) {
		rutFull = this.clearRut(rutFull);
		int rut = 0;
		int arrowPos = rutFull.indexOf('-');
		if (arrowPos >= 0) {
			rut = Integer.parseInt(rutFull.substring(0, arrowPos));
		} else {
			arrowPos = rutFull.length() - 1;
			rut = Integer.parseInt(rutFull.substring(0, arrowPos));
		}

		return rut;
	}

	@Override
	public String calculateFullRut(int rut) {
		return "" + rut + "-" + dv(rut);
	}
}
