package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPenjualanDetailRacikan is a Querydsl query type for PenjualanDetailRacikan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenjualanDetailRacikan extends EntityPathBase<PenjualanDetailRacikan> {

    private static final long serialVersionUID = -2094872329L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPenjualanDetailRacikan penjualanDetailRacikan = new QPenjualanDetailRacikan("penjualanDetailRacikan");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> hargaJualPerKomposisi = createNumber("hargaJualPerKomposisi", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final id.edmaputra.uwati.entity.master.obat.QObat komposisi;

    public final QPenjualanRacikan penjualanRacikan;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPenjualanDetailRacikan(String variable) {
        this(PenjualanDetailRacikan.class, forVariable(variable), INITS);
    }

    public QPenjualanDetailRacikan(Path<? extends PenjualanDetailRacikan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanDetailRacikan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanDetailRacikan(PathMetadata<?> metadata, PathInits inits) {
        this(PenjualanDetailRacikan.class, metadata, inits);
    }

    public QPenjualanDetailRacikan(Class<? extends PenjualanDetailRacikan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.komposisi = inits.isInitialized("komposisi") ? new id.edmaputra.uwati.entity.master.obat.QObat(forProperty("komposisi"), inits.get("komposisi")) : null;
        this.penjualanRacikan = inits.isInitialized("penjualanRacikan") ? new QPenjualanRacikan(forProperty("penjualanRacikan"), inits.get("penjualanRacikan")) : null;
    }

}

