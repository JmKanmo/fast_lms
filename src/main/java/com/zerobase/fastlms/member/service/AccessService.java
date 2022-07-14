package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.AccessDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.model.AccessData;

import java.util.List;

public interface AccessService {
    /**
     * 특정 회원의 접속 정보 저장
     */
    boolean saveAccessData(AccessData accessData);

    /**
     * 특정 회원의 접속 정보 반환
     */
    List<AccessDto> accessList(MemberParam memberParam);
}
