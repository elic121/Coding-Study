import requests

url = "http://localhost:8000/items/"

# Item 모델에 맞게 JSON 데이터 생성
data = {"name": "example", "description": "test"}

# POST 요청 보내기
response = requests.post(url, json=data)

# 응답 출력
print(response.status_code)
print(response.json())
