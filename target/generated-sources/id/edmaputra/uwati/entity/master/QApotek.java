package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QApotek is a Querydsl query type for Apotek
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApotek extends EntityPathBase<Apotek> {

    private static final long serialVersionUID = -394311893L;

    public static final QApotek apotek = new QApotek("apotek");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath alamat = createString("alamat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nama = createString("nama");

    public final StringPath telepon = createString("telepon");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QApotek(String variable) {
        super(Apotek.class, forVariable(variable));
    }

    public QApotek(Path<? extends Apotek> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApotek(PathMetadata<?> metadata) {
        super(Apotek.class, metadata);
    }

}

