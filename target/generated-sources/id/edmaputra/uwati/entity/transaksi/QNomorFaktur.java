package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QNomorFaktur is a Querydsl query type for NomorFaktur
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNomorFaktur extends EntityPathBase<NomorFaktur> {

    private static final long serialVersionUID = -1351826385L;

    public static final QNomorFaktur nomorFaktur = new QNomorFaktur("nomorFaktur");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final BooleanPath isSelesai = createBoolean("isSelesai");

    public final BooleanPath isTerpakai = createBoolean("isTerpakai");

    public final NumberPath<Integer> nomor = createNumber("nomor", Integer.class);

    public final DateTimePath<java.util.Date> tanggal = createDateTime("tanggal", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QNomorFaktur(String variable) {
        super(NomorFaktur.class, forVariable(variable));
    }

    public QNomorFaktur(Path<? extends NomorFaktur> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNomorFaktur(PathMetadata<?> metadata) {
        super(NomorFaktur.class, metadata);
    }

}

