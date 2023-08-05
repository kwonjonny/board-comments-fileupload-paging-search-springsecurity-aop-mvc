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

    // ReadMember 
    MemberConvertDTO readMember(String email);

    // Already User Email Exists
    void alreadyUserEmailExists(String email);

    // Not Found User 
    void notFoundUser(String email);

    // Validation User Email 
    void validationUserEmail(String email);
}
