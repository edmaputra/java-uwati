package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRacikan is a Querydsl query type for Racikan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRacikan extends EntityPathBase<Racikan> {

    private static final long serialVersionUID = 453890668L;

    public static final QRacikan racikan = new QRacikan("racikan");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<java.math.BigDecimal> biayaRacik = createNumber("biayaRacik", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath nama = createString("nama");

    public final ListPath<RacikanDetail, QRacikanDetail> racikanDetail = this.<RacikanDetail, QRacikanDetail>createList("racikanDetail", RacikanDetail.class, QRacikanDetail.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QRacikan(String variable) {
        super(Racikan.class, forVariable(variable));
    }

    public QRacikan(Path<? extends Racikan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRacikan(PathMetadata<?> metadata) {
        super(Racikan.class, metadata);
    }

}

