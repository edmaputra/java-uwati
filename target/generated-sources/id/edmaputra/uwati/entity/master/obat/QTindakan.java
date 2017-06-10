package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTindakan is a Querydsl query type for Tindakan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTindakan extends EntityPathBase<Tindakan> {

    private static final long serialVersionUID = -803528487L;

    public static final QTindakan tindakan = new QTindakan("tindakan");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath kode = createString("kode");

    public final StringPath nama = createString("nama");

    public final NumberPath<java.math.BigDecimal> tarif = createNumber("tarif", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QTindakan(String variable) {
        super(Tindakan.class, forVariable(variable));
    }

    public QTindakan(Path<? extends Tindakan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTindakan(PathMetadata<?> metadata) {
        super(Tindakan.class, metadata);
    }

}

