package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.QDiagnosa;

public class DiagnosaPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QDiagnosa.diagnosa.nama.containsIgnoreCase(cari)
					.or(QDiagnosa.diagnosa.kode.containsIgnoreCase(cari))
					.or(QDiagnosa.diagnosa.userInput.containsIgnoreCase(cari))
					.or(QDiagnosa.diagnosa.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QDiagnosa.diagnosa.nama.containsIgnoreCase(cari)
					.or(QDiagnosa.diagnosa.kode.containsIgnoreCase(cari))
					.or(QDiagnosa.diagnosa.userInput.containsIgnoreCase(cari))
					.or(QDiagnosa.diagnosa.userEditor.containsIgnoreCase(cari)));
		}
	}

	public void nama(String nama) {
		if (hasil == null) {
			hasil = QDiagnosa.diagnosa.nama.containsIgnoreCase(nama)
					.or(QDiagnosa.diagnosa.kode.containsIgnoreCase(nama));			
		} else {
			hasil = hasil.and(QDiagnosa.diagnosa.nama.containsIgnoreCase(nama)
					.or(QDiagnosa.diagnosa.kode.containsIgnoreCase(nama)));
		}		
	}

}
