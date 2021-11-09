# Assignment1_MvvmApiMovie
Image Cache: sử dụng thư viện glide để có thể load image lên view nhanh hơn và glide hỗ trợ việc lưu ảnh vào cache với phương thức DiskCacheStratery.

Custom progress: đầu tiên tạo drawable vs thẻ layer-list gồm 2 item là item để set progressDrawable và 1 item dùng để set progress của progressBar. Trong thẻ item của progress ta set cho chạy từ tọa độ 270 kèm theo đó thì sử dụng gradient để tạo màu từ màu cam tới màu lục cho thanh progress.

Get dữ liệu từ url về bằng cách sử dụng httpUrl để lấy các data sau đó chuyển các dữ liệu ta cần thành list các đối tượng. 

Các activity sẽ nhận những dữ liệu thông qua việc lắng nghe sự thay đổi dữ liệu của các mutableLiveData từ viewModel mà từ đó sẽ dùng adapter đổ dữ liệu lên recycleView.
