package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pengguna.QRole;

public class RolePredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QRole.role.nama.containsIgnoreCase(cari)
					.or(QRole.role.userInput.containsIgnoreCase(cari))
					.or(QRole.role.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QRole.role.nama.containsIgnoreCase(cari)
					.or(QRole.role.userInput.containsIgnoreCase(cari))
					.or(QRole.role.userEditor.containsIgnoreCase(cari)));
		}
	}

}
