package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QNomorFaktur;

public class NomorFakturPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	public void tanggal(Date tanggal){
		if (hasil == null){
			hasil = QNomorFaktur.nomorFaktur.tanggal.eq(tanggal);
		} else {
			hasil = hasil.and(QNomorFaktur.nomorFaktur.tanggal.eq(tanggal));
		} 
	}
	
	public void isDone(Boolean b){
		if (hasil == null){
			hasil = QNomorFaktur.nomorFaktur.isDone.eq(b);
		} else {
			hasil = hasil.and(QNomorFaktur.nomorFaktur.isDone.eq(b));
		}
	}

}
