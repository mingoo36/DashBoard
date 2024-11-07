<template>
  <div class="dashboard-stats">
    <!-- Kakao 지도 영역 -->
    <div id="map" class="map"></div>
  </div>
</template>

<script>
import { onMounted } from "vue";

export default {
  name: "DashboardStats",
  setup() {
    // Kakao 지도 초기화 함수
    const initMap = () => {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(36.7989764, 127.0750025),
        level: 5,
      };

      const map = new window.kakao.maps.Map(container, options);

      // 지도 타입 컨트롤 추가 (일반 지도와 스카이뷰 전환)
      const mapTypeControl = new window.kakao.maps.MapTypeControl();
      map.addControl(mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT);

      // 줌 컨트롤 추가
      const zoomControl = new window.kakao.maps.ZoomControl();
      map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);

      const markerPositions = [
        { lat: 36.80006721245233, lng: 127.07720518112183, title: "원화관" },
        { lat: 36.79907925165256, lng: 127.07850337028503, title: "보건의료관" },
        { lat: 36.79876997434826, lng: 127.07589626312256, title: "인문관" },
        { lat: 36.80013593968605, lng: 127.07265615463257, title: "공학관" },
        { lat: 36.80025835991832, lng: 127.0749118924141, title: "본관" },
        { lat: 36.79879145197919, lng: 127.07402281348453, title: "자연관" },
        { lat: 36.80158921562479, lng: 127.06996906303178, title: "스포츠과학관" },
      ];

      markerPositions.forEach(position => {
        const marker = new window.kakao.maps.Marker({
          position: new window.kakao.maps.LatLng(position.lat, position.lng),
          title: position.title,
        });

        marker.setMap(map);

        const infowindow = new window.kakao.maps.InfoWindow({
          content: `<div style="padding:5px;">${position.title}</div>`,
        });

        window.kakao.maps.event.addListener(marker, "click", () => {
          infowindow.open(map, marker);
        });

        // 마커에 호버 이벤트 추가 (마우스오버 시 title 변경)
        window.kakao.maps.event.addListener(marker, "mouseover", () => {
          marker.setTitle(`Hovering over ${position.title}`);
        });

        window.kakao.maps.event.addListener(marker, "mouseout", () => {
          marker.setTitle(position.title); // 호버 후 다시 원래 title로 돌아오기
        });
      });
    };

    onMounted(() => {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=d81c9a970deb0410d13b18cea48ff72c&autoload=false`;
      script.onload = () => window.kakao.maps.load(initMap);
      document.head.appendChild(script);
    });

    return {};
  },
};
</script>

<style scoped>
.dashboard-stats {
  display: flex;
  justify-content: center;
  align-items: center;
}

#map {
  width: 500px;
  height: 250px; /* 지도 높이 조정 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  overflow: hidden;
}
</style>
