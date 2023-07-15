package board.file.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.file.dto.member.MemberReadDTO;
import board.file.dto.member.MemberConvertDTO;

// MemberMapper Interface 
@Mapper
public interface MemberMapper {
    
    // Security Member Read 
    MemberReadDTO selectOne(String email);

    // Create Role 
    @Insert("INSERT INTO tbl_member_role (email, rolename) VALUES (#{email}, #{rolename})")
    int createJoinMemberRole(@Param("email") String email, @Param("rolename") String rolename);

    // Create Member
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Delete Member
    int deleteMember(String email);

    // Delete Member Role
    int deleteMemberRole(String email);

    // Update Member 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Read Member 
    MemberConvertDTO selectMember(String email);
}
