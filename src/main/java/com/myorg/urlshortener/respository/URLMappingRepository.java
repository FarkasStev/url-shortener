package com.myorg.urlshortener.respository;

import com.myorg.urlshortener.model.URLMapping;

public interface URLMappingRepository {
    URLMapping findByShortCode(String code);
    URLMapping save(URLMapping url);
}
