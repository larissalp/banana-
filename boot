@@ -428,6 +428,25 @@ func TestSlashRemovingWithQuery(t *testing.T) {
	}
}

func TestSubRouteExtracting(t *testing.T) {
	mux := New()
	apiMux := New()
	result := ""
	apiMux.GetFunc("/test", func(rw http.ResponseWriter, req *http.Request) {
		result = mux.GetRequestRoute(req)
	})
	mux.SubRoute("/api", apiMux)

	req, _ := http.NewRequest("GET", "/api/test", nil)
	rw := httptest.NewRecorder()
	mux.ServeHTTP(rw, req)

	if result != "/test" {
		t.Log(result)
		t.Error("SubRouter route extracting not working")
	}
}

func TestNew(t *testing.T) {
	type args struct {
		adapters []adapter
