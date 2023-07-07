package board.file.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.file.dto.member.MemberDTO;
import board.file.dto.member.MemberReadDTO;

@Mapper
public interface MemberMapper {
    
    // Read Member
    MemberReadDTO readMember(String email);

    // Join Member
    @Insert("INSERT INTO tbl_member (email, memberPassword, memberName) VALUES (#{email}, #{memberPassword}, #{memberName})")
    int joinMember(@Param("email") String email, @Param("memberPassword") String memberPassword, @Param("memberName") String memberName);

    @Insert("INSERT INTO tbl_member_role (email, rolename) VALUES (#{email}, #{rolename})")
    int createJoinMemberRole(@Param("email") String email, @Param("rolename") String rolename);
}
