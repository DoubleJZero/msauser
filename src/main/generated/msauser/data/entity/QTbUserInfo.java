package msauser.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTbUserInfo is a Querydsl query type for TbUserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTbUserInfo extends EntityPathBase<TbUserInfo> {

    private static final long serialVersionUID = -889016682L;

    public static final QTbUserInfo tbUserInfo = new QTbUserInfo("tbUserInfo");

    public final msauser.data.QBaseTimeEntity _super = new msauser.data.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> rgstDttm = _super.rgstDttm;

    //inherited
    public final StringPath rgstId = _super.rgstId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updtDttm = _super.updtDttm;

    //inherited
    public final StringPath updtId = _super.updtId;

    public final StringPath userBirth = createString("userBirth");

    public final StringPath userId = createString("userId");

    public final StringPath userNm = createString("userNm");

    public final StringPath userPw = createString("userPw");

    public QTbUserInfo(String variable) {
        super(TbUserInfo.class, forVariable(variable));
    }

    public QTbUserInfo(Path<? extends TbUserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTbUserInfo(PathMetadata metadata) {
        super(TbUserInfo.class, metadata);
    }

}

