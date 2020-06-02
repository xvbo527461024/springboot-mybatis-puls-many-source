package cn.sm.com.base;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.extension.activerecord.Model;
@KeySequence("seq_id")
public abstract class EntityBase<T> extends Model {
}
