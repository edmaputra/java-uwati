package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QPenjualanDetailTemp;

public class PenjualanDetailTempPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	public void find(String nomorFaktur, String pengguna){
		if (hasil == null) {
			hasil = QPenjualanDetailTemp.penjualanDetailTemp.nomorFaktur.containsIgnoreCase(nomorFaktur)
					.and(QPenjualanDetailTemp.penjualanDetailTemp.pengguna.containsIgnoreCase(pengguna));
		} 
	}

}
