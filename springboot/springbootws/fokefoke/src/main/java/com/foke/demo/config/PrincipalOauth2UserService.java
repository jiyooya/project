package com.foke.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.foke.demo.dto.MemberDTO;
import com.foke.demo.repository.MemberRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;

	// 구글로부터 받은 userRequest 데이터에 대한 후처리가 되는 함수
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId로 어떤
																								// OAuth로 로그인하는지 알 수 있음
		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());

		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인완료 -> code 리턴받음(OAuth-Client라이브러리) ->
		// AccessToken요청
		// userRequest 정보 -> loadUser 함수 호출 -> 구글로부터 회원프로필을 받아준다
		System.out.println("getAttribute : " + oauth2User.getAttributes());
		
		String provider = userRequest.getClientRegistration().getRegistrationId(); // google
		String providerId = oauth2User.getAttribute("sub");
		String username = oauth2User.getAttribute("name"); // google_1234646843542315646
		String password = bCryptPasswordEncoder.encode("0000");
		String email = oauth2User.getAttribute("email");
		Integer role = 0;
		
		Optional<MemberDTO> OptionalMemberEntity = memberRepository.findBymemberId(email);
		MemberDTO memberEntity = new MemberDTO();
		if(OptionalMemberEntity.isEmpty()) {
			System.out.println("구글 로그인이 최초입니다.");
			memberEntity = MemberDTO.builder()
					.memberId(email)
					.memberPw(password)
					.memberName(username)
					.adminCk(role)
					.provider(provider)
					.providerId(providerId)
					.build();
			memberRepository.save(memberEntity);
		}else {
			System.out.println("구글 로그인을 이미 한 적이 있습니다. 자동회원가입이 되어 있습니다.");
			memberEntity = OptionalMemberEntity.get();
		}
		
		// 회원가입을 강제로 진행해봄.
		return new PrincipalDetails(memberEntity, oauth2User.getAttributes());
	}
}
