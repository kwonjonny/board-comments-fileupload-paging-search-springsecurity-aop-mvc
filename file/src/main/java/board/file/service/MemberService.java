package board.file.service;

import board.file.dto.member.MemberConvertDTO;

// Member Service Interface 
public interface MemberService {

    // Create Member Service 
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Update Member Service 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Delete Member Service 
    int deleteMember(String email);

    // readMember 
    MemberConvertDTO readMember(String email);
}
