package board.file.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.file.dto.member.MemberReadDTO;
import board.file.dto.member.MemberConvertDTO;
import board.file.dto.member.MemberDTO;

@Mapper
public interface MemberMapper {
    
    /*
     * Security Member Read 
     */
    MemberReadDTO selectOne(String email);

    // // Join Member
    // @Insert("INSERT INTO tbl_member (email, memberPassword, memberName) VALUES (#{email}, #{memberPassword}, #{memberName})")
    // int joinMember(@Param("email") String email, @Param("memberPassword") String memberPassword, @Param("memberName") String memberName);

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
