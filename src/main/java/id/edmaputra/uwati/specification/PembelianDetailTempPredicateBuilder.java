package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QPembelianDetailTemp;

public class PembelianDetailTempPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	public void find(String nomorFaktur, String pengguna){
		if (hasil == null) {
			hasil = QPembelianDetailTemp.pembelianDetailTemp.nomorFaktur.containsIgnoreCase(nomorFaktur)
					.and(QPembelianDetailTemp.pembelianDetailTemp.pengguna.containsIgnoreCase(pengguna));
		} 
	}

}
