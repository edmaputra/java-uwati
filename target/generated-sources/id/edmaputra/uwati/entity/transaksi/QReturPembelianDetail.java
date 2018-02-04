package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QReturPembelianDetail is a Querydsl query type for ReturPembelianDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReturPembelianDetail extends EntityPathBase<ReturPembelianDetail> {

    private static final long serialVersionUID = -1917758375L;

    public static final QReturPembelianDetail returPembelianDetail = new QReturPembelianDetail("returPembelianDetail");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaBeli = createNumber("hargaBeli", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaTotal = createNumber("hargaTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idPembelian = createNumber("idPembelian", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final StringPath obat = createString("obat");

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final StringPath pengguna = createString("pengguna");

    public final StringPath supplier = createString("supplier");

    public final DateTimePath<java.util.Date> tanggal = createDateTime("tanggal", java.util.Date.class);

    public final DateTimePath<java.util.Date> tanggalKadaluarsa = createDateTime("tanggalKadaluarsa", java.util.Date.class);

    public final DatePath<java.util.Date> tanggalPembelian = createDate("tanggalPembelian", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QReturPembelianDetail(String variable) {
        super(ReturPembelianDetail.class, forVariable(variable));
    }

    public QReturPembelianDetail(Path<? extends ReturPembelianDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReturPembelianDetail(PathMetadata<?> metadata) {
        super(ReturPembelianDetail.class, metadata);
    }

}

