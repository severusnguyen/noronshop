package com.example.noronshopcms.service.comment;

import com.example.noronshopcommons.data.dto.response.CommentResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import org.springframework.stereotype.Service;

@Service
public interface ICommentService {
    Page<CommentResponse> searchCustom(SearchRequest searchRequest);
}
