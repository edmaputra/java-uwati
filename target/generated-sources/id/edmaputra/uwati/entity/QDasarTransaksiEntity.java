package id.edmaputra.uwati.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDasarTransaksiEntity is a Querydsl query type for DasarTransaksiEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QDasarTransaksiEntity extends EntityPathBase<DasarTransaksiEntity<?>> {

    private static final long serialVersionUID = -248775713L;

    public static final QDasarTransaksiEntity dasarTransaksiEntity = new QDasarTransaksiEntity("dasarTransaksiEntity");

    public final StringPath info = createString("info");

    public final DateTimePath<java.util.Date> terakhirDirubah = createDateTime("terakhirDirubah", java.util.Date.class);

    public final DateTimePath<java.util.Date> waktuDibuat = createDateTime("waktuDibuat", java.util.Date.class);

    @SuppressWarnings("all")
    public QDasarTransaksiEntity(String variable) {
        super((Class)DasarTransaksiEntity.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QDasarTransaksiEntity(Path<? extends DasarTransaksiEntity> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    @SuppressWarnings("all")
    public QDasarTransaksiEntity(PathMetadata<?> metadata) {
        super((Class)DasarTransaksiEntity.class, metadata);
    }

}

