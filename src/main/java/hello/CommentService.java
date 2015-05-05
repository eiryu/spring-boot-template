package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> get() {
        return commentRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    public void register(String comment) throws AppException {
        if ("e".equals(comment)) {
            throw new AppException(String.format("この文字列はコメントとして登録出来ません %s", comment));
        }
        commentRepository.save(new Comment(comment));
    }

}
