package board.file.service;

import board.file.dto.member.MemberConvertDTO;

public interface MemberService {
    
    // Read Member Service 
    MemberConvertDTO selectMember(String email);

    // Create Member Service 
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Update Member Service 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Delete Member Service 
    int deleteMember(String email);
}
