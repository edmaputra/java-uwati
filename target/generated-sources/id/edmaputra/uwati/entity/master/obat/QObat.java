package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QObat is a Querydsl query type for Obat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObat extends EntityPathBase<Obat> {

    private static final long serialVersionUID = 30202461L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObat obat = new QObat("obat");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath barcode = createString("barcode");

    public final StringPath batch = createString("batch");

    public final ListPath<ObatExpired, QObatExpired> expired = this.<ObatExpired, QObatExpired>createList("expired", ObatExpired.class, QObatExpired.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final id.edmaputra.uwati.entity.master.QKategori kategori;

    public final StringPath kode = createString("kode");

    public final StringPath nama = createString("nama");

    public final ListPath<ObatDetail, QObatDetail> obatDetail = this.<ObatDetail, QObatDetail>createList("obatDetail", ObatDetail.class, QObatDetail.class, PathInits.DIRECT2);

    public final id.edmaputra.uwati.entity.master.QSatuan satuan;

    public final ListPath<ObatStok, QObatStok> stok = this.<ObatStok, QObatStok>createList("stok", ObatStok.class, QObatStok.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stokMinimal = createNumber("stokMinimal", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    public final NumberPath<Integer> tipe = createNumber("tipe", Integer.class);

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QObat(String variable) {
        this(Obat.class, forVariable(variable), INITS);
    }

    public QObat(Path<? extends Obat> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObat(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObat(PathMetadata<?> metadata, PathInits inits) {
        this(Obat.class, metadata, inits);
    }

    public QObat(Class<? extends Obat> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kategori = inits.isInitialized("kategori") ? new id.edmaputra.uwati.entity.master.QKategori(forProperty("kategori")) : null;
        this.satuan = inits.isInitialized("satuan") ? new id.edmaputra.uwati.entity.master.QSatuan(forProperty("satuan")) : null;
    }

}

