package com.notebook.web;

import com.notebook.entity.ShortNote;
import com.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.notebook.dto.ResData.resData;

@RestController
@RequestMapping(value = "/note")
public class NodeController {
    @Autowired
    NoteService noteService;

    @RequestMapping(value = "/addLike")
    public Map addLike(
            @RequestParam(value = "sid", required = false) String sid
    ) {
        return resData(noteService.updateLikeNumber(sid));
    }

    @RequestMapping(value = "/findPublicNote")
    public Map getPublicNote(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize
    ) {
        int offset = (page - 1) * pageSize;
        List result = noteService.getPublicNote(offset, pageSize);
        return resData(result.size(), result);
    }
}
